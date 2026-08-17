package org.jetbrains.kotlin.incremental.storage;

import java.io.File;
import kotlin.Metadata;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.text.StringsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0016\u0018\u0000 \f2\u00020\u0001:\u0001\fB\u0011\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0003H\u0016J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u0007H\u0016R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/incremental/storage/RelativeFileToPathConverter;", "Lorg/jetbrains/kotlin/incremental/storage/FileToPathConverter;", "baseDirFile", "Ljava/io/File;", "<init>", "(Ljava/io/File;)V", "baseDirPath", "", "toPath", "file", "toFile", "path", "Companion", "org.jetbrains.kotlin:kotlin-build-common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public class RelativeFileToPathConverter implements FileToPathConverter {
    private static final Companion Companion = new Companion(null);
    private final String baseDirPath;

    public RelativeFileToPathConverter(File file) {
        File fileNormalize;
        this.baseDirPath = (file == null || (fileNormalize = FilesKt.normalize(file)) == null) ? null : FilesKt.getInvariantSeparatorsPath(fileNormalize);
    }

    @Override // org.jetbrains.kotlin.incremental.storage.FileToPathConverter
    public File toFile(String path) {
        path.getClass();
        if (!StringsKt.startsWith$default(path, "$PROJECT_DIR$", false, 2, (Object) null)) {
            return new File(path);
        }
        String str = this.baseDirPath;
        if (str != null) {
            return new File(str.concat(path.substring(13)));
        }
        k2d.a("Could not get project root dir");
        return null;
    }

    @Override // org.jetbrains.kotlin.incremental.storage.FileToPathConverter
    public String toPath(File file) {
        file.getClass();
        String invariantSeparatorsPath = FilesKt.getInvariantSeparatorsPath(FilesKt.normalize(file));
        String str = this.baseDirPath;
        return (str == null || !StringsKt.startsWith$default(invariantSeparatorsPath, str, false, 2, (Object) null)) ? invariantSeparatorsPath : "$PROJECT_DIR$".concat(invariantSeparatorsPath.substring(this.baseDirPath.length()));
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lorg/jetbrains/kotlin/incremental/storage/RelativeFileToPathConverter$Companion;", "", "<init>", "()V", "PROJECT_DIR_PLACEHOLDER", "", "org.jetbrains.kotlin:kotlin-build-common"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
