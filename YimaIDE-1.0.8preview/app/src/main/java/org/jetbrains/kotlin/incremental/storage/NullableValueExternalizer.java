package org.jetbrains.kotlin.incremental.storage;

import com.intellij.util.io.DataExternalizer;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0015\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u001f\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00018\u0000H\u0016¢\u0006\u0002\u0010\u000bJ\u0017\u0010\f\u001a\u0004\u0018\u00018\u00002\u0006\u0010\r\u001a\u00020\u000eH\u0016¢\u0006\u0002\u0010\u000fR\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/incremental/storage/NullableValueExternalizer;", "T", "Lcom/intellij/util/io/DataExternalizer;", "valueExternalizer", "<init>", "(Lcom/intellij/util/io/DataExternalizer;)V", "save", "", "output", "Ljava/io/DataOutput;", "value", "(Ljava/io/DataOutput;Ljava/lang/Object;)V", "read", "input", "Ljava/io/DataInput;", "(Ljava/io/DataInput;)Ljava/lang/Object;", "org.jetbrains.kotlin:kotlin-build-common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class NullableValueExternalizer<T> implements DataExternalizer<T> {
    private final DataExternalizer<T> valueExternalizer;

    public NullableValueExternalizer(DataExternalizer<T> dataExternalizer) {
        dataExternalizer.getClass();
        this.valueExternalizer = dataExternalizer;
    }

    public T read(DataInput input) {
        input.getClass();
        if (input.readBoolean()) {
            return (T) this.valueExternalizer.read(input);
        }
        return null;
    }

    public void save(DataOutput output, T value) throws IOException {
        output.getClass();
        output.writeBoolean(value != null);
        if (value != null) {
            this.valueExternalizer.save(output, value);
        }
    }
}
