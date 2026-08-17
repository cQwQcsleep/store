package org.jetbrains.kotlin.incremental.storage;

import com.intellij.util.io.AppendablePersistentMap;
import com.intellij.util.io.DataExternalizer;
import com.intellij.util.io.KeyDescriptor;
import java.io.DataOutput;
import java.io.File;
import java.util.Collection;
import kotlin.Metadata;
import org.jetbrains.kotlin.incremental.storage.AppendableLazyStorage;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u0014\u0012\u0004\u0012\u0002H\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00040\u00032\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0005B+\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\t\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00010\u000b¢\u0006\u0004\b\f\u0010\rJ#\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00028\u00002\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028\u00010\u0004H\u0016¢\u0006\u0002\u0010\u0014R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00010\u000fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/incremental/storage/AppendableLazyStorage;", "KEY", "E", "Lorg/jetbrains/kotlin/incremental/storage/LazyStorage;", "", "Lorg/jetbrains/kotlin/incremental/storage/AppendablePersistentStorage;", "storageFile", "Ljava/io/File;", "keyDescriptor", "Lcom/intellij/util/io/KeyDescriptor;", "elementExternalizer", "Lcom/intellij/util/io/DataExternalizer;", "<init>", "(Ljava/io/File;Lcom/intellij/util/io/KeyDescriptor;Lcom/intellij/util/io/DataExternalizer;)V", "appendableCollectionExternalizer", "Lorg/jetbrains/kotlin/incremental/storage/AppendableCollectionExternalizer;", "append", "", "key", "elements", "(Ljava/lang/Object;Ljava/util/Collection;)V", "org.jetbrains.kotlin:kotlin-build-common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class AppendableLazyStorage<KEY, E> extends LazyStorage<KEY, Collection<? extends E>> implements AppendablePersistentStorage<KEY, E> {
    private final AppendableCollectionExternalizer<E> appendableCollectionExternalizer;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AppendableLazyStorage(File file, KeyDescriptor<KEY> keyDescriptor, DataExternalizer<E> dataExternalizer) {
        super(file, keyDescriptor, new AppendableCollectionExternalizer(dataExternalizer));
        file.getClass();
        keyDescriptor.getClass();
        dataExternalizer.getClass();
        this.appendableCollectionExternalizer = new AppendableCollectionExternalizer<>(dataExternalizer);
    }

    public static void c(AppendableLazyStorage appendableLazyStorage, Collection collection, DataOutput dataOutput) {
        dataOutput.getClass();
        appendableLazyStorage.appendableCollectionExternalizer.append(dataOutput, collection);
    }

    @Override // org.jetbrains.kotlin.incremental.storage.AppendablePersistentStorage
    public synchronized void append(KEY key, final Collection<? extends E> elements) {
        elements.getClass();
        getStorageOrCreateNew().appendData(key, new AppendablePersistentMap.ValueDataAppender() { // from class: pd0
            public final void append(DataOutput dataOutput) {
                AppendableLazyStorage.c(this.a, elements, dataOutput);
            }
        });
    }
}
