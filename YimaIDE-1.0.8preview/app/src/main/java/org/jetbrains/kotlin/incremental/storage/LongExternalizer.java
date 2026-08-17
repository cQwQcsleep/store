package org.jetbrains.kotlin.incremental.storage;

import com.intellij.util.io.DataExternalizer;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0002H\u0016J\u0015\u0010\n\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\fH\u0016¢\u0006\u0002\u0010\r¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/incremental/storage/LongExternalizer;", "Lcom/intellij/util/io/DataExternalizer;", "", "<init>", "()V", "save", "", "output", "Ljava/io/DataOutput;", "value", "read", "input", "Ljava/io/DataInput;", "(Ljava/io/DataInput;)Ljava/lang/Long;", "org.jetbrains.kotlin:kotlin-build-common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class LongExternalizer implements DataExternalizer<Long> {
    public static final LongExternalizer INSTANCE = new LongExternalizer();

    private LongExternalizer() {
    }

    /* JADX INFO: renamed from: read, reason: merged with bridge method [inline-methods] */
    public Long m129read(DataInput input) {
        input.getClass();
        return Long.valueOf(input.readLong());
    }

    public /* bridge */ /* synthetic */ void save(DataOutput dataOutput, Object obj) throws IOException {
        save(dataOutput, ((Number) obj).longValue());
    }

    public void save(DataOutput output, long value) throws IOException {
        output.getClass();
        output.writeLong(value);
    }
}
