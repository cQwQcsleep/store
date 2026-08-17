package com.android.tools.r8;

import com.android.tools.r8.origin.Origin;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface DataEntryResource extends DataResource {
    static DataEntryResource fromBytes(byte[] bArr, String str, Origin origin) {
        return new C3518u(bArr, str, origin);
    }

    static DataEntryResource fromFile(Path path, Path path2) {
        return new C3520v(path2.toString().replace(File.separatorChar, DataResource.SEPARATOR), path.resolve(path2).toFile());
    }

    static DataEntryResource fromString(String str, Origin origin, String... strArr) {
        StringBuilder sb = new StringBuilder();
        for (String str2 : strArr) {
            sb.append(str2);
            sb.append(System.lineSeparator());
        }
        return new C3518u(sb.toString().getBytes(), str, origin);
    }

    static DataEntryResource fromZip(ZipFile zipFile, ZipEntry zipEntry) {
        return new C3522x(zipFile, zipEntry);
    }

    InputStream getByteStream() throws ResourceException;

    default DataEntryResource withName(String str) {
        return new C3521w(str, this);
    }
}
