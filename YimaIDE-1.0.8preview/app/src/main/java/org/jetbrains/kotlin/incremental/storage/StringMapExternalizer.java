package org.jetbrains.kotlin.incremental.storage;

import com.intellij.util.io.DataExternalizer;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\b&\u0018\u0000*\u0004\b\u0000\u0010\u00012\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u0002H\u00010\u00030\u0002B\u0007¢\u0006\u0004\b\u0005\u0010\u0006J&\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0014\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0003H\u0016J\u001e\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u00032\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u001d\u0010\u000f\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00028\u0000H$¢\u0006\u0002\u0010\u0011J\u0015\u0010\u0012\u001a\u00028\u00002\u0006\u0010\r\u001a\u00020\u000eH$¢\u0006\u0002\u0010\u0013¨\u0006\u0014"}, d2 = {"Lorg/jetbrains/kotlin/incremental/storage/StringMapExternalizer;", "T", "Lcom/intellij/util/io/DataExternalizer;", "", "", "<init>", "()V", "save", "", "output", "Ljava/io/DataOutput;", "map", "read", "input", "Ljava/io/DataInput;", "writeValue", "value", "(Ljava/io/DataOutput;Ljava/lang/Object;)V", "readValue", "(Ljava/io/DataInput;)Ljava/lang/Object;", "org.jetbrains.kotlin:kotlin-build-common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public abstract class StringMapExternalizer<T> implements DataExternalizer<Map<String, ? extends T>> {
    public Map<String, T> read(DataInput input) throws IOException {
        input.getClass();
        int i = input.readInt();
        HashMap map = new HashMap(i);
        for (int i2 = 0; i2 < i; i2++) {
            map.put(ExternalizersKt.readString(input), readValue(input));
        }
        return map;
    }

    public abstract T readValue(DataInput input);

    public void save(DataOutput output, Map<String, ? extends T> map) throws IOException {
        output.getClass();
        map.getClass();
        output.writeInt(map.size());
        for (Map.Entry<String, ? extends T> entry : map.entrySet()) {
            String key = entry.getKey();
            T value = entry.getValue();
            ExternalizersKt.writeString(output, key);
            writeValue(output, value);
        }
    }

    public abstract void writeValue(DataOutput output, T value);
}
