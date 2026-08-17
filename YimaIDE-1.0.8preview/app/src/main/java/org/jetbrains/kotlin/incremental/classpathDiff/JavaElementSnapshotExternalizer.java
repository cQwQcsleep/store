package org.jetbrains.kotlin.incremental.classpathDiff;

import com.intellij.util.io.DataExternalizer;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import kotlin.Metadata;
import org.jetbrains.kotlin.incremental.storage.LongExternalizer;
import org.jetbrains.kotlin.incremental.storage.StringExternalizer;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bÂ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0002H\u0016J\u0010\u0010\n\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\fH\u0016¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/incremental/classpathDiff/JavaElementSnapshotExternalizer;", "Lcom/intellij/util/io/DataExternalizer;", "Lorg/jetbrains/kotlin/incremental/classpathDiff/JavaElementSnapshot;", "<init>", "()V", "save", "", "output", "Ljava/io/DataOutput;", "value", "read", "input", "Ljava/io/DataInput;", "org.jetbrains.kotlin:incremental-compilation-impl"}, k = 1, mv = {2, 4, 0}, xi = 48)
final class JavaElementSnapshotExternalizer implements DataExternalizer<JavaElementSnapshot> {
    public static final JavaElementSnapshotExternalizer INSTANCE = new JavaElementSnapshotExternalizer();

    private JavaElementSnapshotExternalizer() {
    }

    public JavaElementSnapshot read(DataInput input) {
        input.getClass();
        return new JavaElementSnapshot(StringExternalizer.INSTANCE.read(input), LongExternalizer.INSTANCE.m129read(input).longValue());
    }

    public void save(DataOutput output, JavaElementSnapshot value) throws IOException {
        output.getClass();
        value.getClass();
        StringExternalizer.INSTANCE.save(output, value.getName());
        LongExternalizer.INSTANCE.save(output, value.getAbiHash());
    }
}
