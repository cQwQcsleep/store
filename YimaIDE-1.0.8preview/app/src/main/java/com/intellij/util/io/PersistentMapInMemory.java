package com.intellij.util.io;

import com.intellij.util.Processor;
import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class PersistentMapInMemory<Key, Value> implements PersistentMapBase<Key, Value> {
    private final AtomicBoolean myIsClosed;
    private final Object myLock;
    private final ConcurrentHashMap<Key, Value> myMap;
    private final DataExternalizer<Value> myValueExternalizer;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 1 || i == 2) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i == 1 || i == 2) ? 2 : 3];
        if (i == 1 || i == 2) {
            objArr[0] = "com/intellij/util/io/PersistentMapInMemory";
        } else if (i == 3 || i == 4) {
            objArr[0] = "processor";
        } else {
            objArr[0] = "builder";
        }
        if (i == 1) {
            objArr[1] = "getValuesExternalizer";
        } else if (i != 2) {
            objArr[1] = "com/intellij/util/io/PersistentMapInMemory";
        } else {
            objArr[1] = "getDataAccessLock";
        }
        if (i != 1 && i != 2) {
            if (i == 3) {
                objArr[2] = "processKeys";
            } else if (i != 4) {
                objArr[2] = "<init>";
            } else {
                objArr[2] = "processExistingKeys";
            }
        }
        String str2 = String.format(str, objArr);
        if (i != 1 && i != 2) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    public PersistentMapInMemory(PersistentMapBuilder<Key, Value> persistentMapBuilder) {
        if (persistentMapBuilder == null) {
            $$$reportNull$$$0(0);
        }
        this.myLock = new Object();
        this.myMap = new ConcurrentHashMap<>();
        this.myIsClosed = new AtomicBoolean(false);
        this.myValueExternalizer = persistentMapBuilder.getValueExternalizer();
    }

    @Override // com.intellij.util.io.PersistentMapBase
    public void close() throws IOException {
        this.myIsClosed.set(true);
    }

    @Override // com.intellij.util.io.PersistentMapBase
    public void closeAndDelete() {
        this.myMap.clear();
    }

    @Override // com.intellij.util.io.PersistentMapBase
    public boolean containsKey(Key key) throws IOException {
        return this.myMap.containsKey(key);
    }

    @Override // com.intellij.util.io.PersistentMapBase
    public void force() {
    }

    @Override // com.intellij.util.io.PersistentMapBase
    public Value get(Key key) throws IOException {
        return this.myMap.get(key);
    }

    @Override // com.intellij.util.io.PersistentMapBase
    public DataExternalizer<Value> getValuesExternalizer() {
        DataExternalizer<Value> dataExternalizer = this.myValueExternalizer;
        if (dataExternalizer == null) {
            $$$reportNull$$$0(1);
        }
        return dataExternalizer;
    }

    @Override // com.intellij.util.io.PersistentMapBase
    public boolean isClosed() {
        return this.myIsClosed.get();
    }

    @Override // com.intellij.util.io.PersistentMapBase
    public boolean isDirty() {
        return false;
    }

    @Override // com.intellij.util.io.PersistentMapBase
    public int keysCount() {
        return this.myMap.size();
    }

    @Override // com.intellij.util.io.PersistentMapBase
    public void markDirty() throws IOException {
    }

    @Override // com.intellij.util.io.PersistentMapBase
    public boolean processExistingKeys(Processor<? super Key> processor) throws IOException {
        if (processor == null) {
            $$$reportNull$$$0(4);
        }
        Iterator it = this.myMap.keySet().iterator();
        while (it.hasNext()) {
            if (!processor.process((Object) it.next())) {
                return false;
            }
        }
        return true;
    }

    @Override // com.intellij.util.io.PersistentMapBase
    public boolean processKeys(Processor<? super Key> processor) throws IOException {
        if (processor == null) {
            $$$reportNull$$$0(3);
        }
        return processExistingKeys(processor);
    }

    @Override // com.intellij.util.io.PersistentMapBase
    public void put(Key key, Value value) throws IOException {
        this.myMap.put(key, value);
    }

    @Override // com.intellij.util.io.PersistentMapBase
    public void remove(Key key) throws IOException {
        this.myMap.remove(key);
    }
}
