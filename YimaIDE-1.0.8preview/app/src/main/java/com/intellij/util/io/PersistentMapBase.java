package com.intellij.util.io;

import androidx.collection.ScatterMapKt;
import com.intellij.openapi.util.io.BufferExposingByteArrayOutputStream;
import com.intellij.util.Processor;
import com.intellij.util.io.PersistentMapBase;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public interface PersistentMapBase<Key, Value> {
    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        Object[] objArr = new Object[3];
        switch (i) {
            case 1:
            case 5:
                objArr[0] = "originalMap";
                break;
            case 2:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
                objArr[0] = "targetCanonicalMap";
                break;
            case 3:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
                objArr[0] = "stableKeysSorter";
                break;
            case 4:
                objArr[0] = "valueCanonicalizer";
                break;
            default:
                objArr[0] = "appender";
                break;
        }
        objArr[1] = "com/intellij/util/io/PersistentMapBase";
        switch (i) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
                objArr[2] = "canonicalize";
                break;
            default:
                objArr[2] = "appendData";
                break;
        }
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
    }

    static /* synthetic */ boolean a(List list, Object obj) {
        list.add(obj);
        return true;
    }

    static <K, V, M extends PersistentMapBase<? super K, ? super V>> M canonicalize(PersistentMapBase<K, V> persistentMapBase, M m, Function<? super List<K>, ? extends List<K>> function, Function<? super V, ? extends V> function2) throws IOException {
        if (persistentMapBase == null) {
            $$$reportNull$$$0(1);
        }
        if (m == null) {
            $$$reportNull$$$0(2);
        }
        if (function == null) {
            $$$reportNull$$$0(3);
        }
        if (function2 == null) {
            $$$reportNull$$$0(4);
        }
        final ArrayList arrayList = new ArrayList();
        persistentMapBase.processExistingKeys(new Processor() { // from class: y0b
            @Override // com.intellij.util.Processor
            public final boolean process(Object obj) {
                return PersistentMapBase.a(arrayList, obj);
            }
        });
        for (K k : function.apply(arrayList)) {
            m.put(k, function2.apply((Object) persistentMapBase.get(k)));
        }
        return m;
    }

    default void appendData(Key key, AppendablePersistentMap.ValueDataAppender valueDataAppender) throws IOException {
        if (valueDataAppender == null) {
            $$$reportNull$$$0(0);
        }
        BufferExposingByteArrayOutputStream bufferExposingByteArrayOutputStream = new BufferExposingByteArrayOutputStream();
        java.io.DataOutputStream dataOutputStream = new java.io.DataOutputStream(bufferExposingByteArrayOutputStream);
        DataExternalizer<Value> valuesExternalizer = getValuesExternalizer();
        Value value = get(key);
        if (value != null) {
            valuesExternalizer.save(dataOutputStream, value);
        }
        valueDataAppender.append(dataOutputStream);
        dataOutputStream.close();
        DataInputStream dataInputStream = new DataInputStream(bufferExposingByteArrayOutputStream.toInputStream());
        Value value2 = valuesExternalizer.read(dataInputStream);
        dataInputStream.close();
        put(key, value2);
    }

    void close() throws IOException;

    void closeAndDelete() throws IOException;

    boolean containsKey(Key key) throws IOException;

    void force() throws IOException;

    Value get(Key key) throws IOException;

    DataExternalizer<Value> getValuesExternalizer();

    boolean isClosed();

    boolean isDirty();

    default int keysCount() {
        return -1;
    }

    default void markCorrupted() {
    }

    void markDirty() throws IOException;

    boolean processExistingKeys(Processor<? super Key> processor) throws IOException;

    boolean processKeys(Processor<? super Key> processor) throws IOException;

    void put(Key key, Value value) throws IOException;

    void remove(Key key) throws IOException;
}
