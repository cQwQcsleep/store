package org.jetbrains.kotlin.incremental;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.io.FilesKt;
import kotlin.text.StringsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\u0018\u0010\u0003\u001a\u00020\u0001*\u00020\u00022\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u001a\n\u0010\u0007\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\b\u001a\u00020\t*\u00020\u0002\u001a\n\u0010\n\u001a\u00020\t*\u00020\u0002\u001a\n\u0010\u000b\u001a\u00020\t*\u00020\u0002¨\u0006\f"}, d2 = {"isJavaFile", "", "Ljava/io/File;", "isKotlinFile", "sourceFilesExtensions", "", "", "isClassFile", "deleteDirectoryContents", "", "deleteRecursivelyOrThrow", "createDirectory", "org.jetbrains.kotlin:kotlin-build-common"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class FileUtilsKt {
    public static final void createDirectory(File file) throws IOException {
        file.getClass();
        if (file.isDirectory()) {
            return;
        }
        if (file.isFile()) {
            f2f.a("A regular file already exists at this path: ", file.getPath());
            return;
        }
        if (file.mkdirs() || file.isDirectory()) {
            return;
        }
        throw new IOException("Could not create directory '" + file.getPath() + '\'');
    }

    public static final void deleteDirectoryContents(File file) throws IOException {
        file.getClass();
        if (!file.isDirectory()) {
            if (file.isFile()) {
                f2f.a("Expected a directory but found a regular file: ", file.getPath());
                return;
            } else {
                f2f.a("Directory does not exist: ", file.getPath());
                return;
            }
        }
        File[] fileArrListFiles = file.listFiles();
        fileArrListFiles.getClass();
        for (File file2 : fileArrListFiles) {
            file2.getClass();
            deleteRecursivelyOrThrow(file2);
        }
    }

    public static final void deleteRecursivelyOrThrow(File file) throws IOException {
        file.getClass();
        if (FilesKt.deleteRecursively(file)) {
            return;
        }
        throw new IOException("Could not delete '" + file.getPath() + '\'');
    }

    public static final boolean isClassFile(File file) {
        file.getClass();
        return StringsKt.equals(FilesKt.getExtension(file), "class", true);
    }

    public static final boolean isJavaFile(File file) {
        file.getClass();
        return StringsKt.equals(FilesKt.getExtension(file), "java", true);
    }

    public static final boolean isKotlinFile(File file, Collection<String> collection) {
        file.getClass();
        collection.getClass();
        if (isJavaFile(file)) {
            return false;
        }
        Collection<String> collection2 = collection;
        if (collection2.isEmpty()) {
            return false;
        }
        Iterator<T> it = collection2.iterator();
        while (it.hasNext()) {
            if (StringsKt.equals((String) it.next(), FilesKt.getExtension(file), true)) {
                return true;
            }
        }
        return false;
    }
}
