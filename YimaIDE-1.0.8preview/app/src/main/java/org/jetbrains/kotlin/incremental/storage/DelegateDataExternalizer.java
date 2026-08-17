package org.jetbrains.kotlin.incremental.storage;

import com.intellij.util.io.DataExternalizer;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B3\u0012\u0014\u0010\u0003\u001a\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u00050\u0004\u0012\u0014\u0010\u0006\u001a\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u00020\u0004¢\u0006\u0004\b\u0007\u0010\bJ\u001d\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0011J\u0015\u0010\u0012\u001a\u00028\u00002\u0006\u0010\u0013\u001a\u00020\u0014H\u0016¢\u0006\u0002\u0010\u0015R\u001f\u0010\u0003\u001a\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001f\u0010\u0006\u001a\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\n¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/incremental/storage/DelegateDataExternalizer;", "T", "Lcom/intellij/util/io/DataExternalizer;", "types", "", "Ljava/lang/Class;", "typesExternalizers", "<init>", "(Ljava/util/List;Ljava/util/List;)V", "getTypes", "()Ljava/util/List;", "getTypesExternalizers", "save", "", "output", "Ljava/io/DataOutput;", "objectToExternalize", "(Ljava/io/DataOutput;Ljava/lang/Object;)V", "read", "input", "Ljava/io/DataInput;", "(Ljava/io/DataInput;)Ljava/lang/Object;", "org.jetbrains.kotlin:kotlin-build-common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class DelegateDataExternalizer<T> implements DataExternalizer<T> {
    private final List<Class<? extends T>> types;
    private final List<DataExternalizer<? extends T>> typesExternalizers;

    /* JADX WARN: Multi-variable type inference failed */
    public DelegateDataExternalizer(List<? extends Class<? extends T>> list, List<? extends DataExternalizer<? extends T>> list2) {
        list.getClass();
        list2.getClass();
        this.types = list;
        this.typesExternalizers = list2;
        if (list.size() != list2.size()) {
            k2d.a("Check failed.");
            throw null;
        }
        if (list.size() < 127) {
            return;
        }
        k2d.a("Check failed.");
        throw null;
    }

    public final List<Class<? extends T>> getTypes() {
        return this.types;
    }

    public final List<DataExternalizer<? extends T>> getTypesExternalizers() {
        return this.typesExternalizers;
    }

    public T read(DataInput input) throws IOException {
        input.getClass();
        return (T) this.typesExternalizers.get(input.readByte()).read(input);
    }

    public void save(DataOutput output, T objectToExternalize) throws IOException {
        output.getClass();
        Class cls = null;
        boolean z = false;
        for (T t : this.types) {
            objectToExternalize.getClass();
            if (((Class) t).isAssignableFrom(objectToExternalize.getClass())) {
                if (z) {
                    w01.a("Collection contains more than one matching element.");
                    return;
                } else {
                    z = true;
                    cls = t;
                }
            }
        }
        if (!z) {
            hb9.a("Collection contains no element matching the predicate.");
            return;
        }
        int iIndexOf = this.types.indexOf(cls);
        output.writeByte(iIndexOf);
        DataExternalizer<? extends T> dataExternalizer = this.typesExternalizers.get(iIndexOf);
        dataExternalizer.getClass();
        dataExternalizer.save(output, objectToExternalize);
    }
}
