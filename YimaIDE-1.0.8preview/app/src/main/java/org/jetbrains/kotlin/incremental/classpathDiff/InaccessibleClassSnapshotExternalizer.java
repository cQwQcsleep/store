package org.jetbrains.kotlin.incremental.classpathDiff;

import com.intellij.util.io.DataExternalizer;
import java.io.DataInput;
import java.io.DataOutput;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bÂ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0002H\u0016J\u0010\u0010\n\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\fH\u0016¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/incremental/classpathDiff/InaccessibleClassSnapshotExternalizer;", "Lcom/intellij/util/io/DataExternalizer;", "Lorg/jetbrains/kotlin/incremental/classpathDiff/InaccessibleClassSnapshot;", "<init>", "()V", "save", "", "output", "Ljava/io/DataOutput;", "snapshot", "read", "input", "Ljava/io/DataInput;", "org.jetbrains.kotlin:incremental-compilation-impl"}, k = 1, mv = {2, 4, 0}, xi = 48)
final class InaccessibleClassSnapshotExternalizer implements DataExternalizer<InaccessibleClassSnapshot> {
    public static final InaccessibleClassSnapshotExternalizer INSTANCE = new InaccessibleClassSnapshotExternalizer();

    private InaccessibleClassSnapshotExternalizer() {
    }

    public InaccessibleClassSnapshot read(DataInput input) {
        input.getClass();
        return InaccessibleClassSnapshot.INSTANCE;
    }

    public void save(DataOutput output, InaccessibleClassSnapshot snapshot) {
        output.getClass();
        snapshot.getClass();
    }
}
