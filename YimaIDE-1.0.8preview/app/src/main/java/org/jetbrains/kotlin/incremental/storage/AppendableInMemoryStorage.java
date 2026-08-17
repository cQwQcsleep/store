package org.jetbrains.kotlin.incremental.storage;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u0014\u0012\u0004\u0012\u0002H\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00040\u00032\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0005B\u001b\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u001e\u0010\t\u001a\n\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u00042\u0006\u0010\n\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\u000bJ#\u0010\f\u001a\u00020\r2\u0006\u0010\n\u001a\u00028\u00002\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00010\u0004H\u0016¢\u0006\u0002\u0010\u000fJ\b\u0010\u0010\u001a\u00020\rH\u0016R\u001a\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/incremental/storage/AppendableInMemoryStorage;", "KEY", "E", "Lorg/jetbrains/kotlin/incremental/storage/InMemoryStorage;", "", "Lorg/jetbrains/kotlin/incremental/storage/AppendablePersistentStorage;", "storage", "<init>", "(Lorg/jetbrains/kotlin/incremental/storage/AppendablePersistentStorage;)V", "get", "key", "(Ljava/lang/Object;)Ljava/util/Collection;", "append", "", "elements", "(Ljava/lang/Object;Ljava/util/Collection;)V", "applyChanges", "org.jetbrains.kotlin:kotlin-build-common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class AppendableInMemoryStorage<KEY, E> extends InMemoryStorage<KEY, Collection<? extends E>> implements AppendablePersistentStorage<KEY, E> {
    private final AppendablePersistentStorage<KEY, E> storage;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AppendableInMemoryStorage(AppendablePersistentStorage<KEY, E> appendablePersistentStorage) {
        super(appendablePersistentStorage);
        appendablePersistentStorage.getClass();
        this.storage = appendablePersistentStorage;
    }

    @Override // org.jetbrains.kotlin.incremental.storage.AppendablePersistentStorage
    public synchronized void append(KEY key, Collection<? extends E> elements) {
        try {
            elements.getClass();
            if (getAddedEntries().containsKey(key)) {
                LinkedHashMap<KEY, Collection<? extends E>> addedEntries = getAddedEntries();
                Collection<? extends E> collection = getAddedEntries().get(key);
                collection.getClass();
                addedEntries.put(key, CollectionsKt.plus(collection, elements));
            } else if (getModifiedEntries().containsKey(key)) {
                LinkedHashMap<KEY, Collection<? extends E>> modifiedEntries = getModifiedEntries();
                Collection<? extends E> collection2 = getModifiedEntries().get(key);
                collection2.getClass();
                modifiedEntries.put(key, CollectionsKt.plus(collection2, elements));
            } else if (getAppendedEntries().containsKey(key)) {
                LinkedHashMap<KEY, Collection<? extends E>> appendedEntries = getAppendedEntries();
                Collection<? extends E> collection3 = getAppendedEntries().get(key);
                collection3.getClass();
                appendedEntries.put(key, CollectionsKt.plus(collection3, elements));
            } else if (getRemovedKeys().contains(key)) {
                getRemovedKeys().remove(key);
                getModifiedEntries().put(key, elements);
            } else if (this.storage.contains(key)) {
                getAppendedEntries().put(key, elements);
            } else {
                getAddedEntries().put(key, elements);
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // org.jetbrains.kotlin.incremental.storage.InMemoryStorage, org.jetbrains.kotlin.incremental.storage.InMemoryStorageInterface
    public synchronized void applyChanges() {
        try {
            for (Map.Entry<KEY, Collection<? extends E>> entry : getAppendedEntries().entrySet()) {
                this.storage.append((Object) entry.getKey(), (Collection) entry.getValue());
            }
            getAppendedEntries().clear();
            super.applyChanges();
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // org.jetbrains.kotlin.incremental.storage.InMemoryStorage, org.jetbrains.kotlin.incremental.storage.PersistentStorage
    public synchronized Collection<E> get(KEY key) {
        List listPlus;
        try {
            if (getAppendedEntries().containsKey(key)) {
                Collection<? extends E> collection = this.storage.get(key);
                collection.getClass();
                Collection<? extends E> collection2 = getAppendedEntries().get(key);
                collection2.getClass();
                listPlus = CollectionsKt.plus(collection, collection2);
            } else {
                listPlus = (Collection) super.get((Object) key);
            }
        } catch (Throwable th) {
            throw th;
        }
        return listPlus;
    }
}
