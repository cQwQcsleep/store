package org.jetbrains.kotlin.incremental.storage;

import com.intellij.util.io.DataExternalizer;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0002H\u0016¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/incremental/storage/ICClassesAttributesExternalizer;", "Lcom/intellij/util/io/DataExternalizer;", "Lorg/jetbrains/kotlin/incremental/storage/ICClassesAttributes;", "<init>", "()V", "read", "input", "Ljava/io/DataInput;", "save", "", "output", "Ljava/io/DataOutput;", "value", "org.jetbrains.kotlin:kotlin-build-common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ICClassesAttributesExternalizer implements DataExternalizer<ICClassesAttributes> {
    public static final ICClassesAttributesExternalizer INSTANCE = new ICClassesAttributesExternalizer();

    private ICClassesAttributesExternalizer() {
    }

    public ICClassesAttributes read(DataInput input) {
        input.getClass();
        return new ICClassesAttributes(input.readBoolean());
    }

    public void save(DataOutput output, ICClassesAttributes value) throws IOException {
        output.getClass();
        value.getClass();
        output.writeBoolean(value.isSealed());
    }
}
