package org.jetbrains.kotlin.incremental.storage;

import java.util.Collection;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0004\b&\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u0014\u0012\u0004\u0012\u0002H\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00040\u00032\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0005B\u001b\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0005¢\u0006\u0004\b\u0007\u0010\bJ#\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00028\u00002\f\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00010\u0004H\u0016¢\u0006\u0002\u0010\rR\u001a\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/incremental/storage/AppendablePersistentStorageWrapper;", "KEY", "E", "Lorg/jetbrains/kotlin/incremental/storage/PersistentStorageWrapper;", "", "Lorg/jetbrains/kotlin/incremental/storage/AppendablePersistentStorage;", "appendableStorage", "<init>", "(Lorg/jetbrains/kotlin/incremental/storage/AppendablePersistentStorage;)V", "append", "", "key", "elements", "(Ljava/lang/Object;Ljava/util/Collection;)V", "org.jetbrains.kotlin:kotlin-build-common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public abstract class AppendablePersistentStorageWrapper<KEY, E> extends PersistentStorageWrapper<KEY, Collection<? extends E>> implements AppendablePersistentStorage<KEY, E> {
    private final AppendablePersistentStorage<KEY, E> appendableStorage;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AppendablePersistentStorageWrapper(AppendablePersistentStorage<KEY, E> appendablePersistentStorage) {
        super(appendablePersistentStorage);
        appendablePersistentStorage.getClass();
        this.appendableStorage = appendablePersistentStorage;
    }

    @Override // org.jetbrains.kotlin.incremental.storage.AppendablePersistentStorage
    public synchronized void append(KEY key, Collection<? extends E> elements) {
        elements.getClass();
        this.appendableStorage.append((Object) key, (Collection) elements);
    }
}
