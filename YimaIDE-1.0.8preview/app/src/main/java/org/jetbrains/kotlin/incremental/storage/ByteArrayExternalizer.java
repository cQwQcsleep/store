package org.jetbrains.kotlin.incremental.storage;

import com.intellij.util.io.DataExternalizer;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0002H\u0016J\u0010\u0010\n\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\fH\u0016¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/incremental/storage/ByteArrayExternalizer;", "Lcom/intellij/util/io/DataExternalizer;", "", "<init>", "()V", "save", "", "output", "Ljava/io/DataOutput;", "bytes", "read", "input", "Ljava/io/DataInput;", "org.jetbrains.kotlin:kotlin-build-common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ByteArrayExternalizer implements DataExternalizer<byte[]> {
    public static final ByteArrayExternalizer INSTANCE = new ByteArrayExternalizer();

    private ByteArrayExternalizer() {
    }

    public byte[] read(DataInput input) throws IOException {
        input.getClass();
        int i = input.readInt();
        byte[] bArr = new byte[i];
        input.readFully(bArr, 0, i);
        return bArr;
    }

    public void save(DataOutput output, byte[] bytes) throws IOException {
        output.getClass();
        bytes.getClass();
        output.writeInt(bytes.length);
        output.write(bytes);
    }
}
