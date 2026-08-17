package org.jetbrains.kotlin.library;

import java.util.Collection;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u001d\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\u0006R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/library/SerializedIrModule;", "", "files", "", "Lorg/jetbrains/kotlin/library/SerializedIrFile;", "fileWithPreparedInlinableFunctions", "(Ljava/util/Collection;Lorg/jetbrains/kotlin/library/SerializedIrFile;)V", "getFileWithPreparedInlinableFunctions", "()Lorg/jetbrains/kotlin/library/SerializedIrFile;", "getFiles", "()Ljava/util/Collection;", "kotlin-util-klib"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class SerializedIrModule {
    private final SerializedIrFile fileWithPreparedInlinableFunctions;
    private final Collection<SerializedIrFile> files;

    public SerializedIrModule(Collection<SerializedIrFile> collection, SerializedIrFile serializedIrFile) {
        collection.getClass();
        this.files = collection;
        this.fileWithPreparedInlinableFunctions = serializedIrFile;
    }

    public final SerializedIrFile getFileWithPreparedInlinableFunctions() {
        return this.fileWithPreparedInlinableFunctions;
    }

    public final Collection<SerializedIrFile> getFiles() {
        return this.files;
    }
}
