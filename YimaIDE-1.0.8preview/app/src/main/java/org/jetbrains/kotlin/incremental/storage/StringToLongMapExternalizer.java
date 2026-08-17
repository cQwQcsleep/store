package org.jetbrains.kotlin.incremental.storage;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0015\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u0014¢\u0006\u0002\u0010\bJ\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0002H\u0014¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/incremental/storage/StringToLongMapExternalizer;", "Lorg/jetbrains/kotlin/incremental/storage/StringMapExternalizer;", "", "<init>", "()V", "readValue", "input", "Ljava/io/DataInput;", "(Ljava/io/DataInput;)Ljava/lang/Long;", "writeValue", "", "output", "Ljava/io/DataOutput;", "value", "org.jetbrains.kotlin:kotlin-build-common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class StringToLongMapExternalizer extends StringMapExternalizer<Long> {
    public static final StringToLongMapExternalizer INSTANCE = new StringToLongMapExternalizer();

    private StringToLongMapExternalizer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.jetbrains.kotlin.incremental.storage.StringMapExternalizer
    public Long readValue(DataInput input) {
        input.getClass();
        return Long.valueOf(input.readLong());
    }

    @Override // org.jetbrains.kotlin.incremental.storage.StringMapExternalizer
    public /* bridge */ /* synthetic */ void writeValue(DataOutput dataOutput, Long l) throws IOException {
        writeValue(dataOutput, l.longValue());
    }

    public void writeValue(DataOutput output, long value) throws IOException {
        output.getClass();
        output.writeLong(value);
    }
}
