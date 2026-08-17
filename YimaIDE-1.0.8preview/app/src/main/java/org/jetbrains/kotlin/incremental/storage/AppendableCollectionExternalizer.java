package org.jetbrains.kotlin.incremental.storage;

import com.intellij.util.io.DataExternalizer;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u001e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u00030\u0002B\u0015\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\u001c\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003J\u001e\u0010\f\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003H\u0016J\u0016\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\u00032\u0006\u0010\u000f\u001a\u00020\u0010H\u0016R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/incremental/storage/AppendableCollectionExternalizer;", "E", "Lcom/intellij/util/io/DataExternalizer;", "", "elementExternalizer", "<init>", "(Lcom/intellij/util/io/DataExternalizer;)V", "append", "", "output", "Ljava/io/DataOutput;", "elements", "save", "value", "read", "input", "Ljava/io/DataInput;", "org.jetbrains.kotlin:kotlin-build-common"}, k = 1, mv = {2, 2, 0}, xi = 48)
final class AppendableCollectionExternalizer<E> implements DataExternalizer<Collection<? extends E>> {
    private final DataExternalizer<E> elementExternalizer;

    public AppendableCollectionExternalizer(DataExternalizer<E> dataExternalizer) {
        dataExternalizer.getClass();
        this.elementExternalizer = dataExternalizer;
    }

    public final void append(DataOutput output, Collection<? extends E> elements) {
        output.getClass();
        elements.getClass();
        save(output, (Collection) elements);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Collection<E> read(DataInput input) {
        input.getClass();
        ArrayList arrayList = new ArrayList();
        DataInputStream dataInputStream = (DataInputStream) input;
        while (dataInputStream.available() > 0) {
            arrayList.add(this.elementExternalizer.read(dataInputStream));
        }
        return arrayList;
    }

    public void save(DataOutput output, Collection<? extends E> value) {
        output.getClass();
        value.getClass();
        Iterator<T> it = value.iterator();
        while (it.hasNext()) {
            this.elementExternalizer.save(output, it.next());
        }
    }
}
