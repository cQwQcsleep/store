package org.jetbrains.kotlin.incremental.snapshots;

import com.intellij.util.io.DataExternalizer;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bÀ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0002H\u0016J\u0010\u0010\n\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\fH\u0016¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/incremental/snapshots/FileSnapshotExternalizer;", "Lcom/intellij/util/io/DataExternalizer;", "Lorg/jetbrains/kotlin/incremental/snapshots/FileSnapshot;", "<init>", "()V", "save", "", "out", "Ljava/io/DataOutput;", "value", "read", "input", "Ljava/io/DataInput;", "org.jetbrains.kotlin:incremental-compilation-impl"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class FileSnapshotExternalizer implements DataExternalizer<FileSnapshot> {
    public static final FileSnapshotExternalizer INSTANCE = new FileSnapshotExternalizer();

    private FileSnapshotExternalizer() {
    }

    public FileSnapshot read(DataInput input) throws IOException {
        input.getClass();
        long j = input.readLong();
        byte[] bArr = new byte[input.readInt()];
        input.readFully(bArr);
        return new FileSnapshot(j, bArr);
    }

    public void save(DataOutput out, FileSnapshot value) throws IOException {
        out.getClass();
        value.getClass();
        out.writeLong(value.getLength());
        out.writeInt(value.getHash().length);
        out.write(value.getHash());
    }
}
