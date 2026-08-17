package org.jetbrains.kotlin.incremental.storage;

import com.intellij.util.io.DataExternalizer;
import java.io.DataInput;
import java.io.DataOutput;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0015\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u001d\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\fJ\u0015\u0010\r\u001a\u00028\u00002\u0006\u0010\u000e\u001a\u00020\u000fH\u0016¢\u0006\u0002\u0010\u0010R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/incremental/storage/NameExternalizer;", "Name", "Lcom/intellij/util/io/DataExternalizer;", "nameTransformer", "Lorg/jetbrains/kotlin/incremental/storage/NameTransformer;", "<init>", "(Lorg/jetbrains/kotlin/incremental/storage/NameTransformer;)V", "save", "", "output", "Ljava/io/DataOutput;", "name", "(Ljava/io/DataOutput;Ljava/lang/Object;)V", "read", "input", "Ljava/io/DataInput;", "(Ljava/io/DataInput;)Ljava/lang/Object;", "org.jetbrains.kotlin:kotlin-build-common"}, k = 1, mv = {2, 2, 0}, xi = 48)
final class NameExternalizer<Name> implements DataExternalizer<Name> {
    private final NameTransformer<Name> nameTransformer;

    public NameExternalizer(NameTransformer<Name> nameTransformer) {
        nameTransformer.getClass();
        this.nameTransformer = nameTransformer;
    }

    public Name read(DataInput input) {
        input.getClass();
        return this.nameTransformer.asName(StringExternalizer.INSTANCE.read(input));
    }

    public void save(DataOutput output, Name name) {
        output.getClass();
        StringExternalizer.INSTANCE.save(output, this.nameTransformer.asString(name));
    }
}
