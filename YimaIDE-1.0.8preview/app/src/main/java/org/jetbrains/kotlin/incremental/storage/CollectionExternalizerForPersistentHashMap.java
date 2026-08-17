package org.jetbrains.kotlin.incremental.storage;

import com.intellij.util.io.DataExternalizer;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u001e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u001f\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u00030\u0002B)\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\u0012\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00070\u0006¢\u0006\u0004\b\b\u0010\tJ\u001e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003H\u0016J\u0016\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u00032\u0006\u0010\u0010\u001a\u00020\u0011H\u0016R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/incremental/storage/CollectionExternalizerForPersistentHashMap;", "T", "Lcom/intellij/util/io/DataExternalizer;", "", "elementExternalizer", "newCollection", "Lkotlin/Function0;", "", "<init>", "(Lcom/intellij/util/io/DataExternalizer;Lkotlin/jvm/functions/Function0;)V", "save", "", "output", "Ljava/io/DataOutput;", "value", "read", "input", "Ljava/io/DataInput;", "org.jetbrains.kotlin:kotlin-build-common"}, k = 1, mv = {2, 2, 0}, xi = 48)
final class CollectionExternalizerForPersistentHashMap<T> implements DataExternalizer<Collection<? extends T>> {
    private final DataExternalizer<T> elementExternalizer;
    private final Function0<Collection<T>> newCollection;

    /* JADX WARN: Multi-variable type inference failed */
    public CollectionExternalizerForPersistentHashMap(DataExternalizer<T> dataExternalizer, Function0<? extends Collection<T>> function0) {
        dataExternalizer.getClass();
        function0.getClass();
        this.elementExternalizer = dataExternalizer;
        this.newCollection = function0;
    }

    public Collection<T> read(DataInput input) {
        input.getClass();
        ArrayList arrayList = (Collection<T>) ((Collection) this.newCollection.invoke());
        DataInputStream dataInputStream = (DataInputStream) input;
        while (dataInputStream.available() > 0) {
            arrayList.add(this.elementExternalizer.read(dataInputStream));
        }
        return arrayList;
    }

    public void save(DataOutput output, Collection<? extends T> value) {
        output.getClass();
        value.getClass();
        Iterator<T> it = value.iterator();
        while (it.hasNext()) {
            this.elementExternalizer.save(output, it.next());
        }
    }
}
