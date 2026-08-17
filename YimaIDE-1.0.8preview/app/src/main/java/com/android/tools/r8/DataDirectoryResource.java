package com.android.tools.r8;

import com.android.tools.r8.origin.Origin;
import java.io.File;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface DataDirectoryResource extends DataResource {
    static DataDirectoryResource fromFile(Path path, Path path2) {
        return new r(path2.toString().replace(File.separatorChar, DataResource.SEPARATOR), path.resolve(path2).toFile());
    }

    static DataDirectoryResource fromName(String str, Origin origin) {
        return new C3362s(str, origin);
    }

    static DataDirectoryResource fromZip(ZipFile zipFile, ZipEntry zipEntry) {
        return new C3517t(zipFile, zipEntry);
    }
}
