package org.jetbrains.kotlin.konan;

import java.io.IOException;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.konan.file.File;
import org.jetbrains.kotlin.konan.file.FileKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0011\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u00032\b\b\u0002\u0010\u0011\u001a\u00020\u0003J\u0010\u0010\u0012\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\u0003H\u0002J\u0006\u0010\u0014\u001a\u00020\u0015R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001b\u0010\t\u001a\u00020\n8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\f¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/konan/TempFiles;", "", "pathToTemporaryDir", "", "(Ljava/lang/String;)V", "deleteOnExit", "", "getDeleteOnExit", "()Z", "dir", "Lorg/jetbrains/kotlin/konan/file/File;", "getDir", "()Lorg/jetbrains/kotlin/konan/file/File;", "dir$delegate", "Lkotlin/Lazy;", "create", "prefix", "suffix", "createDirForTemporaryFiles", "path", "dispose", "", "kotlin-native-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class TempFiles {
    private final boolean deleteOnExit;

    /* JADX INFO: renamed from: dir$delegate, reason: from kotlin metadata */
    private final Lazy dir;

    public TempFiles(final String str) {
        this.deleteOnExit = str == null || str.length() == 0;
        this.dir = LazyKt.lazy(new Function0<File>() { // from class: org.jetbrains.kotlin.konan.TempFiles$dir$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            public final File invoke() {
                if (this.this$0.getDeleteOnExit()) {
                    return FileKt.createTempDir("konan_temp");
                }
                TempFiles tempFiles = this.this$0;
                String str2 = str;
                str2.getClass();
                return tempFiles.createDirForTemporaryFiles(str2);
            }
        });
    }

    public static /* synthetic */ File create$default(TempFiles tempFiles, String str, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = "";
        }
        return tempFiles.create(str, str2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final File createDirForTemporaryFiles(String path) throws IOException {
        if (new File(path).isFile()) {
            aca.a("Given file is not a directory: ", path);
            return null;
        }
        File file = new File(path);
        if (!file.getExists()) {
            file.mkdirs();
        }
        return file;
    }

    private final File getDir() {
        return (File) this.dir.getValue();
    }

    public final File create(String prefix, String suffix) {
        prefix.getClass();
        suffix.getClass();
        return new File(getDir(), prefix + suffix);
    }

    public final void dispose() throws IOException {
        if (this.deleteOnExit) {
            getDir().deleteRecursively();
        }
    }

    public final boolean getDeleteOnExit() {
        return this.deleteOnExit;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public TempFiles() {
        String str = null;
        this(str, 1, str);
    }

    public /* synthetic */ TempFiles(String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str);
    }
}
