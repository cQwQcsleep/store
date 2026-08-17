package com.intellij.util.io;

import androidx.collection.ScatterMapKt;
import com.intellij.util.CommonProcessors;
import com.intellij.util.Processor;
import com.intellij.util.io.PersistentHashMap;
import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class PersistentHashMap<Key, Value> implements AppendablePersistentMap {
    private final PersistentMapBase<Key, Value> myImpl;

    @Deprecated
    public interface ValueDataAppender extends AppendablePersistentMap.ValueDataAppender {
    }

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 17 || i == 21) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i == 17 || i == 21) ? 2 : 3];
        switch (i) {
            case 1:
                objArr[0] = "impl";
                break;
            case 2:
            case 5:
            case 8:
            case 11:
            case 14:
                objArr[0] = "file";
                break;
            case 3:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case 9:
            case 12:
            case 15:
                objArr[0] = "keyDescriptor";
                break;
            case 4:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 10:
            case 13:
            case 16:
                objArr[0] = "valueExternalizer";
                break;
            case 17:
            case 21:
                objArr[0] = "com/intellij/util/io/PersistentHashMap";
                break;
            case 18:
            case 19:
                objArr[0] = "appender";
                break;
            case 20:
            case 23:
                objArr[0] = "processor";
                break;
            case 22:
                objArr[0] = "consumer";
                break;
            case 24:
                objArr[0] = "originalMap";
                break;
            case 25:
                objArr[0] = "targetCanonicalMap";
                break;
            case 26:
                objArr[0] = "stableKeysSorter";
                break;
            case 27:
                objArr[0] = "valueCanonicalizer";
                break;
            default:
                objArr[0] = "builder";
                break;
        }
        if (i == 17) {
            objArr[1] = "getImpl";
        } else if (i != 21) {
            objArr[1] = "com/intellij/util/io/PersistentHashMap";
        } else {
            objArr[1] = "getAllKeysWithExistingMapping";
        }
        switch (i) {
            case 17:
            case 21:
                break;
            case 18:
            case 19:
                objArr[2] = "appendData";
                break;
            case 20:
                objArr[2] = "processKeys";
                break;
            case 22:
                objArr[2] = "consumeKeysWithExistingMapping";
                break;
            case 23:
                objArr[2] = "processKeysWithExistingMapping";
                break;
            case 24:
            case 25:
            case 26:
            case 27:
                objArr[2] = "canonicalize";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i != 17 && i != 21) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public PersistentHashMap(Path path, KeyDescriptor<Key> keyDescriptor, DataExternalizer<Value> dataExternalizer, int i, int i2, StorageLockContext storageLockContext) throws IOException {
        this(PersistentMapBuilder.newBuilder(path, keyDescriptor, dataExternalizer).withInitialSize(i).withVersion(i2).withStorageLockContext(storageLockContext), true);
        if (path == null) {
            $$$reportNull$$$0(14);
        }
        if (keyDescriptor == null) {
            $$$reportNull$$$0(15);
        }
        if (dataExternalizer == null) {
            $$$reportNull$$$0(16);
        }
    }

    public static /* synthetic */ boolean a(Consumer consumer, Object obj) {
        consumer.accept(obj);
        return true;
    }

    public static <K, V> PersistentHashMap<K, V> canonicalize(PersistentHashMap<K, V> persistentHashMap, PersistentHashMap<K, V> persistentHashMap2, Function<? super List<K>, ? extends List<K>> function, Function<? super V, ? extends V> function2) throws IOException {
        if (persistentHashMap == null) {
            $$$reportNull$$$0(24);
        }
        if (persistentHashMap2 == null) {
            $$$reportNull$$$0(25);
        }
        if (function == null) {
            $$$reportNull$$$0(26);
        }
        if (function2 == null) {
            $$$reportNull$$$0(27);
        }
        PersistentMapBase.canonicalize(((PersistentHashMap) persistentHashMap).myImpl, ((PersistentHashMap) persistentHashMap2).myImpl, function, function2);
        return persistentHashMap2;
    }

    @Deprecated
    public void appendData(Key key, ValueDataAppender valueDataAppender) throws IOException {
        if (valueDataAppender == null) {
            $$$reportNull$$$0(18);
        }
        this.myImpl.appendData(key, valueDataAppender);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.myImpl.close();
    }

    public void closeAndClean() throws IOException {
        this.myImpl.closeAndDelete();
    }

    public void consumeKeysWithExistingMapping(final Consumer<? super Key> consumer) throws IOException {
        if (consumer == null) {
            $$$reportNull$$$0(22);
        }
        this.myImpl.processExistingKeys(new Processor() { // from class: x0b
            @Override // com.intellij.util.Processor
            public final boolean process(Object obj) {
                return PersistentHashMap.a(consumer, obj);
            }
        });
    }

    public boolean containsMapping(Key key) throws IOException {
        return this.myImpl.containsKey(key);
    }

    public void dropMemoryCaches() {
        force();
    }

    @Override // com.intellij.openapi.Forceable
    public void force() throws UncheckedIOException {
        try {
            this.myImpl.force();
        } catch (IOException e) {
            u8i.a(e);
        }
    }

    public Value get(Key key) throws IOException {
        return this.myImpl.get(key);
    }

    @Deprecated
    public Collection<Key> getAllKeysWithExistingMapping() throws IOException {
        ArrayList arrayList = new ArrayList();
        this.myImpl.processExistingKeys(new CommonProcessors.CollectProcessor(arrayList));
        return arrayList;
    }

    public PersistentMapBase<Key, Value> getImpl() {
        PersistentMapBase<Key, Value> persistentMapBase = this.myImpl;
        if (persistentMapBase == null) {
            $$$reportNull$$$0(17);
        }
        return persistentMapBase;
    }

    public boolean isClosed() {
        return this.myImpl.isClosed();
    }

    @Override // com.intellij.openapi.Forceable
    public boolean isDirty() {
        return this.myImpl.isDirty();
    }

    public int keysCountApproximately() {
        return this.myImpl.keysCount();
    }

    public void markCorrupted() {
        this.myImpl.markCorrupted();
    }

    public void markDirty() throws IOException {
        this.myImpl.markDirty();
    }

    public boolean processKeys(Processor<? super Key> processor) throws IOException {
        if (processor == null) {
            $$$reportNull$$$0(20);
        }
        return this.myImpl.processKeys(processor);
    }

    public boolean processKeysWithExistingMapping(Processor<? super Key> processor) throws IOException {
        if (processor == null) {
            $$$reportNull$$$0(23);
        }
        return this.myImpl.processExistingKeys(processor);
    }

    public void put(Key key, Value value) throws IOException {
        this.myImpl.put(key, value);
    }

    public void remove(Key key) throws IOException {
        this.myImpl.remove(key);
    }

    public String toString() {
        return this.myImpl.toString();
    }

    public void appendData(Key key, AppendablePersistentMap.ValueDataAppender valueDataAppender) throws IOException {
        if (valueDataAppender == null) {
            $$$reportNull$$$0(19);
        }
        this.myImpl.appendData(key, valueDataAppender);
    }

    public PersistentHashMap(PersistentMapBase<Key, Value> persistentMapBase) {
        if (persistentMapBase == null) {
            $$$reportNull$$$0(1);
        }
        this.myImpl = persistentMapBase;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @Deprecated
    public PersistentHashMap(File file, KeyDescriptor<Key> keyDescriptor, DataExternalizer<Value> dataExternalizer) throws IOException {
        this(PersistentMapBuilder.newBuilder(file.toPath(), keyDescriptor, dataExternalizer), true);
        if (file == null) {
            $$$reportNull$$$0(2);
        }
        if (keyDescriptor == null) {
            $$$reportNull$$$0(3);
        }
        if (dataExternalizer == null) {
            $$$reportNull$$$0(4);
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public PersistentHashMap(Path path, KeyDescriptor<Key> keyDescriptor, DataExternalizer<Value> dataExternalizer) throws IOException {
        this(PersistentMapBuilder.newBuilder(path, keyDescriptor, dataExternalizer), true);
        if (path == null) {
            $$$reportNull$$$0(5);
        }
        if (keyDescriptor == null) {
            $$$reportNull$$$0(6);
        }
        if (dataExternalizer == null) {
            $$$reportNull$$$0(7);
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public PersistentHashMap(Path path, KeyDescriptor<Key> keyDescriptor, DataExternalizer<Value> dataExternalizer, int i) throws IOException {
        this(PersistentMapBuilder.newBuilder(path, keyDescriptor, dataExternalizer).withInitialSize(i), true);
        if (path == null) {
            $$$reportNull$$$0(8);
        }
        if (keyDescriptor == null) {
            $$$reportNull$$$0(9);
        }
        if (dataExternalizer == null) {
            $$$reportNull$$$0(10);
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public PersistentHashMap(Path path, KeyDescriptor<Key> keyDescriptor, DataExternalizer<Value> dataExternalizer, int i, int i2) throws IOException {
        this(PersistentMapBuilder.newBuilder(path, keyDescriptor, dataExternalizer).withInitialSize(i).withVersion(i2), true);
        if (path == null) {
            $$$reportNull$$$0(11);
        }
        if (keyDescriptor == null) {
            $$$reportNull$$$0(12);
        }
        if (dataExternalizer == null) {
            $$$reportNull$$$0(13);
        }
    }

    public PersistentHashMap(PersistentMapBuilder<Key, Value> persistentMapBuilder, boolean z) throws IOException {
        if (persistentMapBuilder == null) {
            $$$reportNull$$$0(0);
        }
        if (z) {
            persistentMapBuilder.withReadonly(false);
            persistentMapBuilder.inlineValues(false);
        }
        this.myImpl = persistentMapBuilder.build().myImpl;
    }
}
