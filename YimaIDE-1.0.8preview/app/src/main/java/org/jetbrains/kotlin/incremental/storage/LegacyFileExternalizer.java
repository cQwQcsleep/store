package org.jetbrains.kotlin.incremental.storage;

import com.intellij.util.io.DataExternalizer;
import com.intellij.util.io.EnumeratorStringDescriptor;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.File;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0002H\u0016J\u0010\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000eH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/incremental/storage/LegacyFileExternalizer;", "Lcom/intellij/util/io/DataExternalizer;", "Ljava/io/File;", "pathConverter", "Lorg/jetbrains/kotlin/incremental/storage/FileToPathConverter;", "<init>", "(Lorg/jetbrains/kotlin/incremental/storage/FileToPathConverter;)V", "save", "", "output", "Ljava/io/DataOutput;", "file", "read", "input", "Ljava/io/DataInput;", "org.jetbrains.kotlin:kotlin-build-common"}, k = 1, mv = {2, 2, 0}, xi = 48)
final class LegacyFileExternalizer implements DataExternalizer<File> {
    private final FileToPathConverter pathConverter;

    public LegacyFileExternalizer(FileToPathConverter fileToPathConverter) {
        fileToPathConverter.getClass();
        this.pathConverter = fileToPathConverter;
    }

    /* JADX INFO: renamed from: read, reason: merged with bridge method [inline-methods] */
    public File m127read(DataInput input) {
        input.getClass();
        FileToPathConverter fileToPathConverter = this.pathConverter;
        String str = EnumeratorStringDescriptor.INSTANCE.read(input);
        str.getClass();
        return fileToPathConverter.toFile(str);
    }

    public void save(DataOutput output, File file) {
        output.getClass();
        file.getClass();
        EnumeratorStringDescriptor.INSTANCE.save(output, this.pathConverter.toPath(file));
    }
}
