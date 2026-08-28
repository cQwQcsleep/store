package com.shadow.okhttp3.internal.io;

import com.shadow.kotlin.io.CloseableKt;
import com.shadow.okio.Okio;
import com.shadow.okio.Okio__JvmOkioKt;
import com.shadow.okio.Sink;
import com.shadow.okio.Source;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/* loaded from: /workspace/unpacked/classes2.dex */
public interface FileSystem {
    public static final Companion Companion = Companion.$$INSTANCE;
    public static final FileSystem SYSTEM = new Companion.SystemFileSystem();

    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        public static final class SystemFileSystem implements FileSystem {
            @Override // com.shadow.okhttp3.internal.io.FileSystem
            public Sink appendingSink(File file) throws FileNotFoundException {
                CloseableKt.checkNotNullParameter(file, "file");
                try {
                    return Okio.appendingSink(file);
                } catch (FileNotFoundException unused) {
                    file.getParentFile().mkdirs();
                    return Okio.appendingSink(file);
                }
            }

            @Override // com.shadow.okhttp3.internal.io.FileSystem
            public void delete(File file) throws IOException {
                CloseableKt.checkNotNullParameter(file, "file");
                if (file.delete() || !file.exists()) {
                    return;
                }
                throw new IOException("failed to delete " + file);
            }

            @Override // com.shadow.okhttp3.internal.io.FileSystem
            public void deleteContents(File file) throws IOException {
                CloseableKt.checkNotNullParameter(file, "directory");
                File[] fileArrListFiles = file.listFiles();
                if (fileArrListFiles == null) {
                    throw new IOException("not a readable directory: " + file);
                }
                for (File file2 : fileArrListFiles) {
                    if (file2.isDirectory()) {
                        deleteContents(file2);
                    }
                    if (!file2.delete()) {
                        throw new IOException("failed to delete " + file2);
                    }
                }
            }

            @Override // com.shadow.okhttp3.internal.io.FileSystem
            public boolean exists(File file) {
                CloseableKt.checkNotNullParameter(file, "file");
                return file.exists();
            }

            @Override // com.shadow.okhttp3.internal.io.FileSystem
            public void rename(File file, File file2) throws IOException {
                CloseableKt.checkNotNullParameter(file, "from");
                CloseableKt.checkNotNullParameter(file2, "to");
                delete(file2);
                if (file.renameTo(file2)) {
                    return;
                }
                throw new IOException("failed to rename " + file + " to " + file2);
            }

            @Override // com.shadow.okhttp3.internal.io.FileSystem
            public Sink sink(File file) throws FileNotFoundException {
                CloseableKt.checkNotNullParameter(file, "file");
                try {
                    return Okio__JvmOkioKt.sink$default(file, false, 1, null);
                } catch (FileNotFoundException unused) {
                    file.getParentFile().mkdirs();
                    return Okio__JvmOkioKt.sink$default(file, false, 1, null);
                }
            }

            @Override // com.shadow.okhttp3.internal.io.FileSystem
            public long size(File file) {
                CloseableKt.checkNotNullParameter(file, "file");
                return file.length();
            }

            @Override // com.shadow.okhttp3.internal.io.FileSystem
            public Source source(File file) throws FileNotFoundException {
                CloseableKt.checkNotNullParameter(file, "file");
                return Okio.source(file);
            }

            public String toString() {
                return "FileSystem.SYSTEM";
            }
        }

        private Companion() {
        }
    }

    Sink appendingSink(File file) throws FileNotFoundException;

    void delete(File file) throws IOException;

    void deleteContents(File file) throws IOException;

    boolean exists(File file);

    void rename(File file, File file2) throws IOException;

    Sink sink(File file) throws FileNotFoundException;

    long size(File file);

    Source source(File file) throws FileNotFoundException;
}
