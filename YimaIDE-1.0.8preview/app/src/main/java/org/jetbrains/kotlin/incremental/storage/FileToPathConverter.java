package org.jetbrains.kotlin.incremental.storage;

import com.intellij.util.io.KeyDescriptor;
import java.io.File;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0003H&J\u000e\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\tH\u0016ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\nÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/incremental/storage/FileToPathConverter;", "", "toPath", "", "file", "Ljava/io/File;", "toFile", "path", "getFileDescriptor", "Lcom/intellij/util/io/KeyDescriptor;", "org.jetbrains.kotlin:kotlin-build-common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface FileToPathConverter {
    default KeyDescriptor<File> getFileDescriptor() {
        return new FileDescriptor(this);
    }

    File toFile(String path);

    String toPath(File file);
}
