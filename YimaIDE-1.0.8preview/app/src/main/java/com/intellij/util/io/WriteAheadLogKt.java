package com.intellij.util.io;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.util.ConcurrencyUtil;
import com.intellij.util.indexing.impl.IndexStorageUtil;
import com.intellij.util.io.WriteAheadLogKt;
import it.unimi.dsi.fastutil.ints.IntLinkedOpenHashSet;
import it.unimi.dsi.fastutil.ints.IntSet;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.function.Function;
import java.util.zip.CRC32;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function0;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000j\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a(\u0010\u0011\u001a\b\u0012\u0004\u0012\u0002H\u00130\u0012\"\u0004\b\u0000\u0010\u00132\u0006\u0010\u0014\u001a\u00020\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u0002H\u00130\u0017\u001a2\u0010\u0018\u001a\b\u0012\u0004\u0012\u0002H\u00130\u0019\"\u0004\b\u0000\u0010\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u001a\u001a\u00020\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u0002H\u00130\u0017H\u0007\u001aJ\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u0002H\u001d\u0012\u0004\u0012\u0002H\u001e0\u001c\"\u0004\b\u0000\u0010\u001d\"\u0004\b\u0001\u0010\u001e2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u001a\u001a\u00020\u00152\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u0002H\u001d0\u00172\f\u0010 \u001a\b\u0012\u0004\u0012\u0002H\u001e0!\u001aB\u0010\"\u001a\u000e\u0012\u0004\u0012\u0002H\u001d\u0012\u0004\u0012\u0002H\u001e0#\"\u0004\b\u0000\u0010\u001d\"\u0004\b\u0001\u0010\u001e2\u0006\u0010\u0014\u001a\u00020\u00152\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u0002H\u001d0\u00172\f\u0010 \u001a\b\u0012\u0004\u0012\u0002H\u001e0!\u001a:\u0010$\u001a\u0004\u0018\u00010\u0015\"\u0004\b\u0000\u0010\u001d\"\u0004\b\u0001\u0010\u001e2\u0006\u0010\u0014\u001a\u00020\u00152\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u0002H\u001d0\u00172\f\u0010 \u001a\b\u0012\u0004\u0012\u0002H\u001e0!H\u0002\u001a)\u0010%\u001a\u0002H\u001e\"\u0004\b\u0000\u0010\u001e2\u0006\u0010&\u001a\u00020'2\f\u0010 \u001a\b\u0012\u0004\u0012\u0002H\u001e0!H\u0002¢\u0006\u0002\u0010(\u001a)\u0010)\u001a\u00020'\"\u0004\b\u0000\u0010\u001e2\u0006\u0010*\u001a\u0002H\u001e2\f\u0010 \u001a\b\u0012\u0004\u0012\u0002H\u001e0!H\u0002¢\u0006\u0002\u0010+\u001a]\u0010,\u001a\u0002H-\"\u0004\b\u0000\u0010\u001d\"\u0004\b\u0001\u0010\u001e\"\u0004\b\u0002\u0010-2\u0006\u0010\u0014\u001a\u00020\u00152\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u0002H\u001d0\u00172\f\u0010 \u001a\b\u0012\u0004\u0012\u0002H\u001e0!2\u0018\u0010.\u001a\u0014\u0012\u0004\u0012\u0002H\u001d\u0012\u0004\u0012\u0002H\u001e\u0012\u0004\u0012\u0002H-0/H\u0002¢\u0006\u0002\u00100\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\"\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n\"\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000\"\u0014\u0010\r\u001a\u00020\u000e8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010¨\u00061"}, d2 = {"VERSION", "", "checksumGen", "Lkotlin/Function0;", "Ljava/util/zip/CRC32;", "debugWalRecords", "", "getDebugWalRecords", "()Z", "setDebugWalRecords", "(Z)V", "log", "Lcom/intellij/openapi/diagnostic/Logger;", "integerExternalizer", "Lcom/intellij/util/io/EnumeratorIntegerDescriptor;", "getIntegerExternalizer", "()Lcom/intellij/util/io/EnumeratorIntegerDescriptor;", "restoreMemoryEnumeratorFromWal", "", "Data", "walFile", "Ljava/nio/file/Path;", "dataDescriptor", "Lcom/intellij/util/io/KeyDescriptor;", "restorePersistentEnumeratorFromWal", "Lcom/intellij/util/io/PersistentEnumerator;", "outputMapFile", "restorePersistentMapFromWal", "Lcom/intellij/util/io/PersistentMap;", "K", "V", "keyDescriptor", "valueExternalizer", "Lcom/intellij/util/io/DataExternalizer;", "restoreHashMapFromWal", "", "tryCompact", "readData", "array", "", "([BLcom/intellij/util/io/DataExternalizer;)Ljava/lang/Object;", "writeData", "value", "(Ljava/lang/Object;Lcom/intellij/util/io/DataExternalizer;)[B", "restoreFromWal", "R", "accumulator", "Lcom/intellij/util/io/Accumulator;", "(Ljava/nio/file/Path;Lcom/intellij/util/io/KeyDescriptor;Lcom/intellij/util/io/DataExternalizer;Lcom/intellij/util/io/Accumulator;)Ljava/lang/Object;", "intellij.platform.util"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class WriteAheadLogKt {
    private static final Function0<CRC32> checksumGen = new Function0() { // from class: vuf
        public final Object invoke() {
            return WriteAheadLogKt.c();
        }
    };
    private static volatile boolean debugWalRecords;
    private static final Logger log;

    static {
        Logger logger = Logger.getInstance(WalRecord.class);
        logger.getClass();
        log = logger;
    }

    public static IntLinkedOpenHashSet a(Object obj) {
        return new IntLinkedOpenHashSet();
    }

    public static IntLinkedOpenHashSet b(Object obj) {
        return new IntLinkedOpenHashSet();
    }

    public static CRC32 c() {
        return new CRC32();
    }

    public static void d(WalEvent walEvent, DataOutput dataOutput) throws IOException {
        dataOutput.getClass();
        dataOutput.write(((WalEvent.AppendEvent) walEvent).getData());
    }

    public static final boolean getDebugWalRecords() {
        return debugWalRecords;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final EnumeratorIntegerDescriptor getIntegerExternalizer() {
        EnumeratorIntegerDescriptor enumeratorIntegerDescriptor = EnumeratorIntegerDescriptor.INSTANCE;
        enumeratorIntegerDescriptor.getClass();
        return enumeratorIntegerDescriptor;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final <V> V readData(byte[] bArr, DataExternalizer<V> dataExternalizer) {
        return dataExternalizer.read(new DataInputStream(new ByteArrayInputStream(bArr)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final <K, V> Path tryCompact(Path path, KeyDescriptor<K> keyDescriptor, DataExternalizer<V> dataExternalizer) {
        int i = 0;
        if (!Files.exists(path, new LinkOption[0])) {
            return null;
        }
        Map mapCreateKeyDescriptorHashedMap = IndexStorageUtil.createKeyDescriptorHashedMap(keyDescriptor);
        mapCreateKeyDescriptorHashedMap.getClass();
        PersistentMapWalPlayer persistentMapWalPlayer = new PersistentMapWalPlayer(keyDescriptor, dataExternalizer, path);
        try {
            Iterator it = persistentMapWalPlayer.readWal().iterator();
            int i2 = 0;
            while (it.hasNext()) {
                WalEvent walEvent = (WalEvent) it.next();
                if (walEvent instanceof WalEvent.AppendEvent) {
                    ((IntSet) mapCreateKeyDescriptorHashedMap.computeIfAbsent(((WalEvent.AppendEvent) walEvent).getKey(), new Function() { // from class: wuf
                        @Override // java.util.function.Function
                        public final Object apply(Object obj) {
                            return WriteAheadLogKt.b(obj);
                        }
                    })).add(i2);
                } else if (walEvent instanceof WalEvent.PutEvent) {
                    Object key = ((WalEvent.PutEvent) walEvent).getKey();
                    IntLinkedOpenHashSet intLinkedOpenHashSet = new IntLinkedOpenHashSet();
                    intLinkedOpenHashSet.add(i2);
                    Unit unit = Unit.INSTANCE;
                    mapCreateKeyDescriptorHashedMap.put(key, intLinkedOpenHashSet);
                } else {
                    if (!(walEvent instanceof WalEvent.RemoveEvent)) {
                        if (walEvent instanceof WalEvent.CorruptionEvent) {
                            throw new CorruptionException("wal has been corrupted");
                        }
                        throw new NoWhenBranchMatchedException();
                    }
                    mapCreateKeyDescriptorHashedMap.put(((WalEvent.RemoveEvent) walEvent).getKey(), new IntLinkedOpenHashSet());
                }
                ((IntSet) mapCreateKeyDescriptorHashedMap.computeIfAbsent(walEvent.getKey(), new Function() { // from class: xuf
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return WriteAheadLogKt.a(obj);
                    }
                })).add(i2);
                i2++;
            }
            boolean z = mapCreateKeyDescriptorHashedMap.size() * 2 < i2;
            CloseableKt.closeFinally(persistentMapWalPlayer, (Throwable) null);
            if (!z) {
                return null;
            }
            Path pathResolveSibling = path.resolveSibling(path.getFileName() + "_compacted");
            PersistentMapWalPlayer persistentMapWalPlayer2 = new PersistentMapWalPlayer(keyDescriptor, dataExternalizer, path);
            try {
                boolean useCompression$intellij_platform_util = persistentMapWalPlayer2.getUseCompression$intellij_platform_util();
                pathResolveSibling.getClass();
                ExecutorService executorServiceNewSameThreadExecutorService = ConcurrencyUtil.newSameThreadExecutorService();
                executorServiceNewSameThreadExecutorService.getClass();
                PersistentMapWal persistentMapWal = new PersistentMapWal(keyDescriptor, dataExternalizer, useCompression$intellij_platform_util, pathResolveSibling, executorServiceNewSameThreadExecutorService, false, 32, null);
                try {
                    for (Object obj : persistentMapWalPlayer2.readWal()) {
                        int i3 = i + 1;
                        if (i < 0) {
                            CollectionsKt.throwIndexOverflow();
                        }
                        final WalEvent walEvent2 = (WalEvent) obj;
                        Object key2 = walEvent2.getKey();
                        IntSet intSet = (IntSet) mapCreateKeyDescriptorHashedMap.get(key2);
                        if (intSet == null) {
                            throw new IOException("No events found for key =  " + key2);
                        }
                        if (intSet.contains(i)) {
                            if (walEvent2 instanceof WalEvent.AppendEvent) {
                                persistentMapWal.appendData(key2, new AppendablePersistentMap.ValueDataAppender() { // from class: yuf
                                    @Override // com.intellij.util.io.AppendablePersistentMap.ValueDataAppender
                                    public final void append(DataOutput dataOutput) throws IOException {
                                        WriteAheadLogKt.d(walEvent2, dataOutput);
                                    }
                                });
                            } else if (walEvent2 instanceof WalEvent.PutEvent) {
                                persistentMapWal.put(key2, ((WalEvent.PutEvent) walEvent2).getValue());
                            } else if (!(walEvent2 instanceof WalEvent.RemoveEvent)) {
                                if (walEvent2 instanceof WalEvent.CorruptionEvent) {
                                    throw new CorruptionException("wal has been corrupted");
                                }
                                throw new NoWhenBranchMatchedException();
                            }
                        }
                        i = i3;
                    }
                    Unit unit2 = Unit.INSTANCE;
                    CloseableKt.closeFinally(persistentMapWal, (Throwable) null);
                    CloseableKt.closeFinally(persistentMapWalPlayer2, (Throwable) null);
                    return pathResolveSibling;
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        CloseableKt.closeFinally(persistentMapWal, th);
                        throw th2;
                    }
                }
            } catch (Throwable th3) {
                try {
                    throw th3;
                } catch (Throwable th4) {
                    CloseableKt.closeFinally(persistentMapWalPlayer2, th3);
                    throw th4;
                }
            }
        } catch (Throwable th5) {
            try {
                throw th5;
            } catch (Throwable th6) {
                CloseableKt.closeFinally(persistentMapWalPlayer, th5);
                throw th6;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final <V> byte[] writeData(V v, DataExternalizer<V> dataExternalizer) throws IOException {
        UnsyncByteArrayOutputStream unsyncByteArrayOutputStream = new UnsyncByteArrayOutputStream();
        dataExternalizer.save(new DataOutputStream(unsyncByteArrayOutputStream), v);
        byte[] byteArray = unsyncByteArrayOutputStream.toByteArray();
        byteArray.getClass();
        return byteArray;
    }
}
