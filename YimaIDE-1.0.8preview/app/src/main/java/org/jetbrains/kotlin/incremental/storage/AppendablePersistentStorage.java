package org.jetbrains.kotlin.incremental.storage;

import java.util.Collection;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u001e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\bf\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u0014\u0012\u0004\u0012\u0002H\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00040\u0003J#\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00028\u00002\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00010\u0004H&¢\u0006\u0002\u0010\tJ\u001d\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00028\u00002\u0006\u0010\n\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010\u000bø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\fÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/incremental/storage/AppendablePersistentStorage;", "KEY", "E", "Lorg/jetbrains/kotlin/incremental/storage/PersistentStorage;", "", "append", "", "key", "elements", "(Ljava/lang/Object;Ljava/util/Collection;)V", "element", "(Ljava/lang/Object;Ljava/lang/Object;)V", "org.jetbrains.kotlin:kotlin-build-common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface AppendablePersistentStorage<KEY, E> extends PersistentStorage<KEY, Collection<? extends E>> {
    default void append(KEY key, E element) {
        append((Object) key, (Collection) CollectionsKt.listOf(element));
    }

    void append(KEY key, Collection<? extends E> elements);
}
